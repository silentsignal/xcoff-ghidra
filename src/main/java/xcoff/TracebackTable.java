package xcoff;

//This is a generated file! Please edit source .ksy file and use kaitai-struct-compiler to rebuild

import io.kaitai.struct.ByteBufferKaitaiStream;
import io.kaitai.struct.KaitaiStruct;
import io.kaitai.struct.KaitaiStream;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.nio.charset.Charset;


/**
* @see <a href="https://refspecs.linuxfoundation.org/ELF/ppc64/PPC-elf64abi.html#TRACEBACK">Source</a>
*/
public class TracebackTable extends KaitaiStruct {
 public static TracebackTable fromFile(String fileName) throws IOException {
     return new TracebackTable(new ByteBufferKaitaiStream(fileName));
 }

 public enum Lang {
     C(0),
     FORTRAN(1),
     PASCAL(2),
     ADA(3),
     PL_1(4),
     BASIC(5),
     LISP(6),
     COBOL(7),
     MODULA2(8),
     CPP(9),
     RPG(10),
     PL8_PLIX(11),
     ASSEMBLY(12),
     JAVA(13),
     OBJC(14);

     private final long id;
     Lang(long id) { this.id = id; }
     public long id() { return id; }
     private static final Map<Long, Lang> byId = new HashMap<Long, Lang>(15);
     static {
         for (Lang e : Lang.values())
             byId.put(e.id(), e);
     }
     public static Lang byId(long id) { return byId.get(id); }
 }

 public TracebackTable(KaitaiStream _io) {
     this(_io, null, null);
 }

 public TracebackTable(KaitaiStream _io, KaitaiStruct _parent) {
     this(_io, _parent, null);
 }

 public TracebackTable(KaitaiStream _io, KaitaiStruct _parent, TracebackTable _root) {
     super(_io);
     this._parent = _parent;
     this._root = _root == null ? this : _root;
     _read();
 }
 private void _read() {
     this.marker = this._io.readBytes(4);
     if (!(Arrays.equals(marker(), new byte[] { 0, 0, 0, 0 }))) {
         throw new KaitaiStream.ValidationNotEqualError(new byte[] { 0, 0, 0, 0 }, marker(), _io(), "/seq/0");
     }
     this.mandatoryFields = new MandatoryFields(this._io, this, _root);
     this.optionalFields = new OptionalFields(this._io, this, _root);
 }
 public static class MandatoryFields extends KaitaiStruct {
     public static MandatoryFields fromFile(String fileName) throws IOException {
         return new MandatoryFields(new ByteBufferKaitaiStream(fileName));
     }

     public MandatoryFields(KaitaiStream _io) {
         this(_io, null, null);
     }

     public MandatoryFields(KaitaiStream _io, TracebackTable _parent) {
         this(_io, _parent, null);
     }

     public MandatoryFields(KaitaiStream _io, TracebackTable _parent, TracebackTable _root) {
         super(_io);
         this._parent = _parent;
         this._root = _root;
         _read();
     }
     private void _read() {
         this.version = this._io.readU1();
         this.lang = TracebackTable.Lang.byId(this._io.readU1());
         this.globalink = this._io.readBitsIntBe(1) != 0;
         this.isEprol = this._io.readBitsIntBe(1) != 0;
         this.hasTboff = this._io.readBitsIntBe(1) != 0;
         this.intProc = this._io.readBitsIntBe(1) != 0;
         this.hasCtl = this._io.readBitsIntBe(1) != 0;
         this.tocless = this._io.readBitsIntBe(1) != 0;
         this.fbPresent = this._io.readBitsIntBe(1) != 0;
         this.logAbort = this._io.readBitsIntBe(1) != 0;
         this.intHandl = this._io.readBitsIntBe(1) != 0;
         this.namePresent = this._io.readBitsIntBe(1) != 0;
         this.usesAlloca = this._io.readBitsIntBe(1) != 0;
         this.clDisInv = this._io.readBitsIntBe(3);
         this.savesCr = this._io.readBitsIntBe(1) != 0;
         this.savesLr = this._io.readBitsIntBe(1) != 0;
         this.storesBc = this._io.readBitsIntBe(1) != 0;
         this.fixup = this._io.readBitsIntBe(1) != 0;
         this.fpSaved = this._io.readBitsIntBe(6);
         this.hasVecInfo = this._io.readBitsIntBe(1) != 0;
         this.spare4 = this._io.readBitsIntBe(1) != 0;
         this.gprSaved = this._io.readBitsIntBe(6);
         this.fixedparms = this._io.readBitsIntBe(8);
         this.floatparms = this._io.readBitsIntBe(7);
         this.parmsonstk = this._io.readBitsIntBe(1) != 0;
     }
     private int version;
     private Lang lang;
     private boolean globalink;
     private boolean isEprol;
     private boolean hasTboff;
     private boolean intProc;
     private boolean hasCtl;
     private boolean tocless;
     private boolean fbPresent;
     private boolean logAbort;
     private boolean intHandl;
     private boolean namePresent;
     private boolean usesAlloca;
     private long clDisInv;
     private boolean savesCr;
     private boolean savesLr;
     private boolean storesBc;
     private boolean fixup;
     private long fpSaved;
     private boolean hasVecInfo;
     private boolean spare4;
     private long gprSaved;
     private long fixedparms;
     private long floatparms;
     private boolean parmsonstk;
     private TracebackTable _root;
     private TracebackTable _parent;
     public int version() { return version; }
     public Lang lang() { return lang; }
     public boolean globalink() { return globalink; }
     public boolean isEprol() { return isEprol; }
     public boolean hasTboff() { return hasTboff; }
     public boolean intProc() { return intProc; }
     public boolean hasCtl() { return hasCtl; }
     public boolean tocless() { return tocless; }
     public boolean fbPresent() { return fbPresent; }
     public boolean logAbort() { return logAbort; }
     public boolean intHandl() { return intHandl; }
     public boolean namePresent() { return namePresent; }
     public boolean usesAlloca() { return usesAlloca; }
     public long clDisInv() { return clDisInv; }
     public boolean savesCr() { return savesCr; }
     public boolean savesLr() { return savesLr; }
     public boolean storesBc() { return storesBc; }
     public boolean fixup() { return fixup; }
     public long fpSaved() { return fpSaved; }
     public boolean hasVecInfo() { return hasVecInfo; }
     public boolean spare4() { return spare4; }
     public long gprSaved() { return gprSaved; }
     public long fixedparms() { return fixedparms; }
     public long floatparms() { return floatparms; }
     public boolean parmsonstk() { return parmsonstk; }
     public TracebackTable _root() { return _root; }
     public TracebackTable _parent() { return _parent; }
 }
 public static class OptionalFields extends KaitaiStruct {
     public static OptionalFields fromFile(String fileName) throws IOException {
         return new OptionalFields(new ByteBufferKaitaiStream(fileName));
     }

     public OptionalFields(KaitaiStream _io) {
         this(_io, null, null);
     }

     public OptionalFields(KaitaiStream _io, TracebackTable _parent) {
         this(_io, _parent, null);
     }

     public OptionalFields(KaitaiStream _io, TracebackTable _parent, TracebackTable _root) {
         super(_io);
         this._parent = _parent;
         this._root = _root;
         _read();
     }
     private void _read() {
         if ( ((_parent().mandatoryFields().fixedparms() != 0) || (_parent().mandatoryFields().floatparms() != 0)) ) {
             this.parminfo = this._io.readU4be();
         }
         if (_parent().mandatoryFields().hasTboff()) {
             this.tbOffset = this._io.readU4be();
         }
         if (_parent().mandatoryFields().intHandl()) {
             this.handMask = this._io.readU4be();
         }
         if (_parent().mandatoryFields().hasCtl()) {
             this.ctlInfo = this._io.readU4be();
         }
         if (_parent().mandatoryFields().hasCtl()) {
             this.ctlInfoDisp = this._io.readU4be();
         }
         if (_parent().mandatoryFields().namePresent()) {
             this.nameStruct = new NameStruct(this._io, this, _root);
         }
         if (_parent().mandatoryFields().usesAlloca()) {
             this.allocaReg = this._io.readU1();
         }
         this.vrSaved = this._io.readBitsIntBe(6);
         this.savesVrsave = this._io.readBitsIntBe(1) != 0;
         this.hasVarargs = this._io.readBitsIntBe(1) != 0;
         this.vectorparams = this._io.readBitsIntBe(7);
         this.vecPresent = this._io.readBitsIntBe(1) != 0;
     }
     private Long parminfo;
     private Long tbOffset;
     private Long handMask;
     private Long ctlInfo;
     private Long ctlInfoDisp;
     private NameStruct nameStruct;
     private Integer allocaReg;
     private long vrSaved;
     private boolean savesVrsave;
     private boolean hasVarargs;
     private long vectorparams;
     private boolean vecPresent;
     private TracebackTable _root;
     private TracebackTable _parent;
     public Long parminfo() { return parminfo; }
     public Long tbOffset() { return tbOffset; }
     public Long handMask() { return handMask; }
     public Long ctlInfo() { return ctlInfo; }
     public Long ctlInfoDisp() { return ctlInfoDisp; }
     public NameStruct nameStruct() { return nameStruct; }
     public Integer allocaReg() { return allocaReg; }
     public long vrSaved() { return vrSaved; }
     public boolean savesVrsave() { return savesVrsave; }
     public boolean hasVarargs() { return hasVarargs; }
     public long vectorparams() { return vectorparams; }
     public boolean vecPresent() { return vecPresent; }
     public TracebackTable _root() { return _root; }
     public TracebackTable _parent() { return _parent; }
 }
 public static class NameStruct extends KaitaiStruct {
     public static NameStruct fromFile(String fileName) throws IOException {
         return new NameStruct(new ByteBufferKaitaiStream(fileName));
     }

     public NameStruct(KaitaiStream _io) {
         this(_io, null, null);
     }

     public NameStruct(KaitaiStream _io, TracebackTable.OptionalFields _parent) {
         this(_io, _parent, null);
     }

     public NameStruct(KaitaiStream _io, TracebackTable.OptionalFields _parent, TracebackTable _root) {
         super(_io);
         this._parent = _parent;
         this._root = _root;
         _read();
     }
     private void _read() {
         this.nameLen = this._io.readU2be();
         this.name = new String(this._io.readBytes(nameLen()), Charset.forName("ASCII"));
     }
     private int nameLen;
     private String name;
     private TracebackTable _root;
     private TracebackTable.OptionalFields _parent;
     public int nameLen() { return nameLen; }
     public String name() { return name; }
     public TracebackTable _root() { return _root; }
     public TracebackTable.OptionalFields _parent() { return _parent; }
 }
 private byte[] marker;
 private MandatoryFields mandatoryFields;
 private OptionalFields optionalFields;
 private TracebackTable _root;
 private KaitaiStruct _parent;
 public byte[] marker() { return marker; }
 public MandatoryFields mandatoryFields() { return mandatoryFields; }
 public OptionalFields optionalFields() { return optionalFields; }
 public TracebackTable _root() { return _root; }
 public KaitaiStruct _parent() { return _parent; }
}

